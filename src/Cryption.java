public class Cryption {
    private static final char[] ALPHABET = {
            'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я',
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я',
            '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ', '-', '/', '_', ';', '%', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '–', '(', ')'};
    // Количество символов алфавита: 94
    public static final int ALPHABETLENGTH = ALPHABET.length;

    public char crypto(String mode, int key, int characterCode) {
        int newIndex;
        int newKey = key % ALPHABETLENGTH > 0 ? key % ALPHABETLENGTH : ALPHABETLENGTH + key % ALPHABETLENGTH;
        char character = (char) characterCode;
        for (int i = 0; i < ALPHABETLENGTH; i++) {
            if (character == ALPHABET[i]) {
                newIndex = (i + newKey) % ALPHABETLENGTH;
                character = ALPHABET[newIndex];
                break;
            }
        }
        return character;
    }
}
