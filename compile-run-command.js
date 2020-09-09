const fs = require("fs");
const path = require("path");
const readline = require('readline');
const { resolve } = require("path");
let sourceDir = process.argv[2];

async function fileHasStr(file, str) {
    const fileStream = fs.createReadStream(file);

    const rl = readline.createInterface({
        input: fileStream,
        crlfDelay: Infinity
    });
    // Note: we use the crlfDelay option to recognize all instances of CR LF
    // ('\r\n') in input.txt as a single line break.

    for await (const line of rl) {
        // Each line in input.txt will be successively available here as `line`.
        if (line.includes(str)) return true;
    }

    return false;
}


function getDir(src = sourceDir) {
    source = src;
    const bin = path.join(src, "../bin");

    if (!fs.statSync(bin).isDirectory()) {
        fs.mkdirSync(bin);
    }

    return { source, bin };
}



function walkSync(dir, filelist = []) {
    fs.readdirSync(dir).forEach(file => {
        file = path.join(dir, file);

        if (fs.statSync(file).isDirectory()) {
            filelist = walkSync(file, filelist);
        }

        else {
            filelist.push(file);
        }
    });

    return filelist;
};

async function getMain(files, src = sourceDir) {
    let main = null;

    for (const file of files) {
        const hasStr = await fileHasStr(file, "static void main");

        if (hasStr) {
            main = file;
            break;
        }
    }

    if (!main) return "<path.to.mainclass>";

    return main.replace(src, "").split(/[\/\\]/).join(".").replace(".java", "");
}

async function getCommands(source, bin) {
    const files = walkSync(source);
    const main = await getMain(files, source);

    const compile = `javac -d ${bin} -Xlint:unchecked ${files.join(" ")}`;
    const run = `java -cp ${bin} ${main}`;

    return { compile, run };
};

async function display(src) {
    let { source, bin } = getDir(src);
    let { compile, run } = await getCommands(source, bin);

    console.log(`Compile: \n${compile}`);
    console.log(`Run: \n${run}`);

    process.exit(0);
}

const kb = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function askSource() {
    return new Promise(resolve => {
        kb.question(`Enter source:`, resolve);
    });
}

let src = sourceDir;

if (sourceDir) {

    kb.question(`Source directory "${sourceDir}" [Y/n]? -> `, async (name) => {

        if (name.trim().length !== 0 && name.toLowerCase().trim() != "y") {
            src = await askSource();
        }

        await display(src);
    });
} else {
    (async () => {
        src = await askSource();
        await display(src);
    })();
}

kb.on("close", function () {
    console.log("\nBYE BYE !!!");
    process.exit(0);
});

