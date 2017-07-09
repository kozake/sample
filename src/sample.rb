def to_map(file)
    # Map作成
    map = Hash.new
    # fileの全ての行を
    while line = file.gets do
        # Tabで分割
        splited = line.chomp.split(/\t/)
        # 1つめ値をキーで2つめの値を名前に設定
        map[splited[0]] = splited[1]
    end
    return map
end

if ARGV.size < 2
    print "使い方: ruby ./src/sample.rb <fileA> <fileB>\n"
    exit(0)
end

# ファイルA
fileA = open(ARGV[0])
# ファイルB
fileB = open(ARGV[1])

# IDと名前をもったMapを作成
name_map = to_map(fileB)

# fileの全ての行を
while line = fileA.gets do
    # Tabか空白で分割
    splited = line.chomp.split(/\t|\s/)

    outline = "";
    if splited.size > 0
        outline += splited[0] + ":";
        # 名前に変換
        outline += name_map[splited[0]];
    end
    if splited.size > 1
        outline += "," + splited[1];
    end
    if splited.size > 2
        outline += "," + splited[2];
    end
    if splited.size > 3
        outline += "," + splited[3];
    end
    p outline

end

fileA.close
fileB.close