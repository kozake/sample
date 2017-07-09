import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sample {

    public void execute(Path fileA, Path fileB) throws IOException {

        // IDと名前をもったMapを作成
        Map<String, String> nameMap = toMap(fileB);

        // fileAの全ての行を読み込み
        List<String> lines = Files.readAllLines(fileA);

        // fileの全ての行を
        for (String line: lines) {
            // Tabか空白で分割
            String[] splited = line.split("\t|\\s");
            if (splited.length == 0) {
                // 空行のためSkip
                continue;
            }

            String outline = "";
            if (splited.length > 0) {
                outline += splited[0] + ":";
                // 名前に変換
                outline += nameMap.get(splited[0]);
            }
            if (splited.length > 1) {
                outline += "," + splited[1];
            }
            if (splited.length > 2) {
                outline += "," + splited[2];
            }
            if (splited.length > 3) {
                outline += "," + splited[3];
            }
            System.out.println(outline);
        }
    }

    private Map<String, String> toMap(Path file) throws IOException {

        // fileの全ての行を読み込み
        List<String> lines = Files.readAllLines(file);

        // Map作成
        Map<String, String> map = new HashMap<>();

        // fileの全ての行を
        for (String line: lines) {
            // Tabで分割
            String[] splited = line.split("\t");

            // 1つめ値をキーで2つめの値を名前に設定
            map.put(splited[0], splited[1]);
        }
        return map;
    }

    public static void main(String[] args) throws IOException {

        if (args.length < 2) {
            System.out.println("使い方: java Sample <fileA> <fileB>");
            return;
        }

        // ファイルA
        Path fileA = Paths.get(args[0]);

        // ファイルB
        Path fileB = Paths.get(args[1]);

        new Sample().execute(fileA, fileB);
    }
}
