//201404377_진승언
package lexer;

class CharStream {
	private String reader; // 읽어오는 문장들
	private Character cache;
	private int tmp = 0; // 추가(8주차)

	/*
	 * static CharStream from(String str) throws FileNotFoundException { return new
	 * CharStream(new FileReader(str)); }
	 */ // 삭제 (8주차)

	Char nextChar() { // 수정함 (8주차)
		if (cache != null) {
			char ch = cache;
			cache = null;

			return Char.of(ch);
		} else {
			if (tmp > reader.length() - 1) {
				tmp = 0;
				return Char.end();
			} else {
				return Char.of((char) reader.charAt(tmp++));
			}

		}
	}

	CharStream(String reader) { // 수정함(8주차)
		this.reader = reader;
		this.cache = null;
	}

	void pushBack(char ch) {
		cache = ch;
	}
}
