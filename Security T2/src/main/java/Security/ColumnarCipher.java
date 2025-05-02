package Security;
import java.util.*;

public class ColumnarCipher {

    public List<Integer> analyse(String plainText, String cipherText) {
        // TODO: Analyze the plainText and cipherText to determine the key(s)

        String plain = plainText;
        String cipher = cipherText;

        int keylen = 0;
        ArrayList<Integer> key = new ArrayList<>();


        for (int i = 2; i < plain.length(); i++) {
            // 1- loop to get the length of the key
            StringBuilder searchCol = new StringBuilder();

            for (int j = 0; j < plain.length(); j += i) {
                searchCol.append(plain.charAt(j));
            }

            // ckeck if the coll is in the cipher text
            int index = cipher.indexOf(searchCol.toString());

            if (index != -1) {
                keylen = i;
                boolean flag = true;
                ArrayList<String> cols = new ArrayList<>();

                // Initialize with empty strings
                for (int k = 0; k < keylen; k++) {
                    cols.add("");
                }

                for (int col = 0; col < keylen; col++) {
                    StringBuilder temp = new StringBuilder();
                    for (int row = col; row < plain.length(); row += keylen) {
                        temp.append(plain.charAt(row));
                    }
                    cols.set(col, temp.toString());
                }

                for (int k = 0; k < keylen; k++) {
                    index = cipher.indexOf(cols.get(k));
                    if (index != -1) {
                        key.add(index);
                    } else {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    for (int k = 0; k < keylen; k++) {
                        if (key.get(k) == 0)
                            key.set(k, 1);
                        else
                            key.set(k, (key.get(k) / cols.get(0).length()) + 1);    // 0 , 3 , 9 , 6 ->  1 ,2 , 4 , 3
                    }
                    if(encrypt(plainText,key).equals(cipherText)) {
                        System.out.println("match found. when i = " + i);
                        for (int n = 0; n < keylen; n++) {
                            System.out.println("key  : " + key.get(n));
                        }
                        break;
                    }
                }



            } else {
                System.out.println("No match found. when i = " + i);
            }
        }

//        for (int i = 0; i < keylen; i++) {
//            System.out.println("key  : " + key.get(i));
//        }

        return key; // Placeholder return
    }

    public String decrypt(String cipherText, List<Integer> key) {
        int cipherSize = cipherText.length();
        int rows = (int) Math.ceil((double) cipherSize / key.size());
        char[][] grid = new char[rows][key.size()];
        int count = 0;

        Map<Integer, Integer> keyMap = new HashMap<>();
        for (int i = 0; i < key.size(); i++) {
            keyMap.put(key.get(i) - 1, i);
        }

        int remainingCols = cipherSize % key.size();
        for (int i = 0; i < key.size(); i++) {
            for (int j = 0; j < rows; j++) {
                if (remainingCols != 0 && j == rows - 1 && keyMap.get(i) >= remainingCols) continue;
                grid[j][keyMap.get(i)] = cipherText.charAt(count++);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.size(); j++) {
                result.append(grid[i][j]);
            }
        }
        return result.toString().toUpperCase().trim();
    }

    public String encrypt(String plainText, List<Integer> key) {
        int ptSize = plainText.length();
        int rows = (int) Math.ceil((double) ptSize / key.size());
        char[][] grid = new char[rows][key.size()];
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.size(); j++) {
                if (count >= ptSize) {
                    grid[i][j] = 'x';
                } else {
                    grid[i][j] = plainText.charAt(count++);
                }
            }
        }

        Map<Integer, Integer> keyMap = new HashMap<>();
        for (int i = 0; i < key.size(); i++) {
            keyMap.put(key.get(i) - 1, i);
        }

        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < key.size(); i++) {
            for (int j = 0; j < rows; j++) {
                cipherText.append(Character.toUpperCase(grid[j][keyMap.get(i)]));
            }
        }
        return cipherText.toString();
    }
}
