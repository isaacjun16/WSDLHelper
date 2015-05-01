package com.imt.jlex;

import java.lang.System;
import com.imt.token.dto.*;

public class Yylex {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 256;
	private final int YY_EOF = 257;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NOT_ACCEPT,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NOT_ACCEPT,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NOT_ACCEPT,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NOT_ACCEPT,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NOT_ACCEPT,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NOT_ACCEPT,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NOT_ACCEPT,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NOT_ACCEPT,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NOT_ACCEPT,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NOT_ACCEPT,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NOT_ACCEPT,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NOT_ACCEPT,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NOT_ACCEPT,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NOT_ACCEPT,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NOT_ACCEPT,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NOT_ACCEPT,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NOT_ACCEPT,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NOT_ACCEPT,
		/* 154 */ YY_NOT_ACCEPT,
		/* 155 */ YY_NOT_ACCEPT,
		/* 156 */ YY_NOT_ACCEPT,
		/* 157 */ YY_NOT_ACCEPT
	};
	private int yy_cmap[] = unpackFromString(1,258,
"35:8,34:3,35:2,0,35:18,34,35,31,35:10,23,26,29,24:10,30,35,25,28,27,35:2,33" +
":13,21,33:5,11,33:6,35:4,23,35,7,12,18,15,4,16,8,32,13,33:2,20,6,14,9,3,33," +
"10,5,1,19,17,33,22,2,33,35:133,36:2")[0];

	private int yy_rmap[] = unpackFromString(1,158,
"0,1,2:10,3,4,3:13,5,3,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24," +
"25,26,27,28,29,30,13,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48," +
"49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73," +
"74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98," +
"99,100,101,102,103,104,105,106,107,108,109,110,3,111,112,113,114,115,116,11" +
"7,118,119,120,121,122,123,124,125,75,126,127,128,129,130,131")[0];

	private int yy_nxt[][] = unpackFromString(132,37,
"-1,1,135,84,135,141,143,144,135,145,135:2,146,147,135,148,149,135:5,150,135" +
",151,2,3,4,5,6,7,8,152,135,9,10,11,-1,135,86,135:4,88,135:17,-1:7,135:2,-1:" +
"41,135:24,-1:7,135:2,-1:4,135:10,117,135:13,-1:7,135:2,-1:4,56:22,-1,56,-1:" +
"7,56:2,-1:32,65,-1:8,12,135:23,-1:7,135:2,-1:4,31:22,-1,31,-1,58,-1:5,31:2," +
"-1:4,34:19,157,34:2,-1,85,-1:7,34:2,-1:4,13,135:23,-1:7,135:2,-1:4,34:22,-1" +
",34,-1,36,-1:5,34:2,-1:4,135:4,14,135:19,-1:7,135:2,-1:4,38:22,-1,38,-1:7,3" +
"8:2,-1:4,15,135:23,-1:7,135:2,-1:4,38:22,-1,38,-1,36,-1:2,42,44,-1,38:2,-1:" +
"4,16,135:23,-1:7,135:2,-1:4,38:22,-1,40,-1,46,-1:2,42,44,-1,38:2,-1:4,135:4" +
",17,135:19,-1:7,135:2,-1:4,48:22,-1,48,-1:7,48:2,-1:4,135:4,47,135:19,-1:5," +
"29,-1,135:2,-1:27,50,-1:13,18,135:23,-1:7,135:2,-1:4,38:22,-1,52,-1:7,38:2," +
"-1:4,135:24,-1:5,29,-1,135:2,-1:4,48:22,-1,48,-1:4,27,-1:2,48:2,-1:4,135:3," +
"19,135:20,-1:7,135:2,-1:27,50,-1:4,42,-1:8,135:3,20,135:20,-1:7,135:2,-1:4," +
"38:22,-1,52,-1,54,-1:2,42,44,-1,38:2,-1:4,135:4,21,135:19,-1:7,135:2,-1:4,1" +
"35:7,22,135:16,-1:7,135:2,-1:4,56:22,-1,56,-1,58,-1:2,27,-1:2,56:2,-1:4,135" +
":3,23,135:20,-1:7,135:2,-1:4,31:22,-1,31,-1:7,31:2,-1:4,135:13,24,135:10,-1" +
":7,135:2,-1:4,34:22,-1,34,-1,36,-1:2,42,44,-1,34:2,-1:4,135:4,25,135:19,-1:" +
"7,135:2,-1:4,135:13,26,135:10,-1:7,135:2,-1:4,135:3,28,135:20,-1:7,135:2,-1" +
":4,135:9,30,135:14,-1:7,135:2,-1:32,32,-1:8,60,34:21,-1,34,-1,36,-1:5,34:2," +
"-1:4,38:22,-1,40,-1:7,38:2,-1:4,135:9,33,135:14,-1:7,135:2,-1:4,135:3,35,13" +
"5:20,-1:7,135:2,-1:4,135:18,37,135:5,-1:7,135:2,-1:4,135:19,39,135:4,-1:7,1" +
"35:2,-1:4,135:13,41,135:10,-1:7,135:2,-1:4,135:2,43,135:21,-1:7,135:2,-1:4," +
"135:18,45,135:5,-1:7,135:2,-1:4,135:17,49,135:6,-1:7,135:2,-1:4,135:7,51,13" +
"5:16,-1:7,135:2,-1:4,135:4,53,135:19,-1:7,135:2,-1:4,135:13,55,135:10,-1:7," +
"135:2,-1:4,135:2,57,135:21,-1:7,135:2,-1:4,135:8,59,135:15,-1:7,135:2,-1:4," +
"135:13,61,135:10,-1:7,135:2,-1:4,135:8,62,135:15,-1:7,135:2,-1:4,135:17,63," +
"135:6,-1:7,135:2,-1:4,135:6,64,135,68,135:15,-1:7,135:2,-1:4,34:22,-1,85,-1" +
",67,-1:5,34:2,-1:4,135:2,69,135:21,-1:7,135:2,-1:4,34:4,66,34:17,-1,34,-1,3" +
"6,-1:5,34:2,-1:4,135:9,101,135:14,-1:7,135:2,-1:4,135:4,138,135:19,-1:7,135" +
":2,-1:4,135:14,103,135:9,-1:7,135:2,-1:4,135:3,139,135:20,-1:7,135:2,-1:4,1" +
"04,135:23,-1:7,135:2,-1:4,135:13,105,135:10,-1:7,135:2,-1:4,135:2,70,135:21" +
",-1:7,135:2,-1:4,135:15,106,135:8,-1:7,135:2,-1:4,135:17,107,135:6,-1:7,135" +
":2,-1:4,135:18,71,135:5,-1:7,135:2,-1:4,135:19,72,135:4,-1:7,135:2,-1:4,135" +
":23,99,-1:7,135:2,-1:4,73,135:23,-1:7,135:2,-1:4,135:7,108,135:16,-1:7,135:" +
"2,-1:4,135:16,109,135:7,-1:7,135:2,-1:4,135:9,111,135:14,-1:7,135:2,-1:4,13" +
"5:2,74,135:21,-1:7,135:2,-1:4,135:14,113,135:9,-1:7,135:2,-1:4,135:12,114,1" +
"35:11,-1:7,135:2,-1:4,135:18,115,135:5,-1:7,135:2,-1:4,135:3,116,135:20,-1:" +
"7,135:2,-1:4,135:12,75,135:11,-1:7,135:2,-1:4,135:6,76,135:17,-1:7,135:2,-1" +
":4,135:3,77,135:20,-1:7,135:2,-1:4,135:6,140,135:17,-1:7,135:2,-1:4,135:12," +
"78,135:11,-1:7,135:2,-1:4,135:13,118,135:10,-1:7,135:2,-1:4,135:5,119,135:1" +
"8,-1:7,135:2,-1:4,120,135:23,-1:7,135:2,-1:4,135,79,135:22,-1:7,135:2,-1:4," +
"135:12,122,135:11,-1:7,135:2,-1:4,135:3,123,135:20,-1:7,135:2,-1:4,135:20,1" +
"24,135:3,-1:7,135:2,-1:4,135:12,80,135:11,-1:7,135:2,-1:4,125,135:23,-1:7,1" +
"35:2,-1:4,135:13,126,135:10,-1:7,135:2,-1:4,135:6,127,135:17,-1:7,135:2,-1:" +
"4,135:12,128,135:11,-1:7,135:2,-1:4,129,135:23,-1:7,135:2,-1:4,135:5,130,13" +
"5:18,-1:7,135:2,-1:4,135:8,81,135:15,-1:7,135:2,-1:4,135:6,131,135:17,-1:7," +
"135:2,-1:4,135:3,142,135:20,-1:7,135:2,-1:4,132,135:23,-1:7,135:2,-1:4,135:" +
"12,82,135:11,-1:7,135:2,-1:4,135:2,134,135:21,-1:7,135:2,-1:4,135:6,83,135:" +
"17,-1:7,135:2,-1:4,34:8,87,34:13,-1,34,-1,36,-1:5,34:2,-1:4,135:9,102,135:1" +
"4,-1:7,135:2,-1:4,135:4,110,135:19,-1:7,135:2,-1:4,135:9,112,135:14,-1:7,13" +
"5:2,-1:4,121,135:23,-1:7,135:2,-1:4,135:3,137,135:20,-1:7,135:2,-1:4,135:4," +
"133,135:19,-1:7,135:2,-1:4,135:3,89,135:20,-1:7,135:2,-1:4,135:14,90,135:9," +
"-1:7,135:2,-1:4,135:2,91,135:15,92,135:5,-1:7,135:2,-1:4,135:12,93,135:11,-" +
"1:7,135:2,-1:4,135:13,94,135:10,-1:7,135:2,-1:4,135:3,95,135:4,96,135:15,-1" +
":7,135:2,-1:4,135:6,97,135:17,-1:7,135:2,-1:4,135:5,98,135:18,-1:7,135:2,-1" +
":4,100,135:23,-1:7,135:2,-1:4,34:22,-1,34,-1,36,-1:5,136,34,-1:4,34:19,153," +
"34:2,-1,34,-1,36,-1:5,34:2,-1:4,34:6,154,34:15,-1,34,-1,36,-1:5,34:2,-1:4,3" +
"4:17,155,34:4,-1,34,-1,36,-1:5,34:2,-1:4,34:8,156,34:13,-1,34,-1,36,-1:5,34" +
":2,-1:3");

	public Yytoken yylex ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

	System.out.println("FIN DEL ARCHIVO");
	return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -2:
						break;
					case 2:
						{ return (new Yytoken(1003, "tkn_sign_parAngularAp", yytext(), yyline, yychar)); }
					case -3:
						break;
					case 3:
						{ return (new Yytoken(1004, "tkn_sign_punto", yytext(), yyline, yychar)); }
					case -4:
						break;
					case 4:
						{ return (new Yytoken(1005, "tkn_sign_parAngularcer", yytext(), yyline, yychar)); }
					case -5:
						break;
					case 5:
						{ return (new Yytoken(1006, "tkn_sign_igual", yytext(), yyline, yychar)); }
					case -6:
						break;
					case 6:
						{ return (new Yytoken(1007, "tkn_sign_diagonal", yytext(), yyline, yychar)); }
					case -7:
						break;
					case 7:
						{ return (new Yytoken(1008, "tkn_sign_dosPuntos", yytext(), yyline, yychar)); }
					case -8:
						break;
					case 8:
						{ return (new Yytoken(1009, "tkn_sign_comillaDoble", yytext(), yyline, yychar)); }
					case -9:
						break;
					case 9:
						{ }
					case -10:
						break;
					case 10:
						{
	System.out.println("Error lexico en Fila: "+yyline+" y columna:  "+ yychar + " No se reconoce " + yytext() );
	yychar=0;
}
					case -11:
						break;
					case 11:
						
					case -12:
						break;
					case 12:
						{ return (new Yytoken(15, "tkn_tag_part", yytext(), yyline, yychar)); }
					case -13:
						break;
					case 13:
						{ return (new Yytoken(12, "tkn_tag_port", yytext(), yyline, yychar)); }
					case -14:
						break;
					case 14:
						{ return (new Yytoken(1, "tkn_tag_types", yytext(), yyline, yychar)); }
					case -15:
						break;
					case 15:
						{ return (new Yytoken(17, "tkn_tag_input", yytext(), yyline, yychar)); }
					case -16:
						break;
					case 16:
						{ return (new Yytoken(19, "tkn_tag_fault", yytext(), yyline, yychar)); }
					case -17:
						break;
					case 17:
						{ return (new Yytoken(103, "tkn_attr_xmlns", yytext(), yyline, yychar)); }
					case -18:
						break;
					case 18:
						{ return (new Yytoken(18, "tkn_tag_output", yytext(), yyline, yychar)); }
					case -19:
						break;
					case 19:
						{ return (new Yytoken(13, "tkn_tag_service", yytext(), yyline, yychar)); }
					case -20:
						break;
					case 20:
						{ return (new Yytoken(2, "tkn_tag_message", yytext(), yyline, yychar)); }
					case -21:
						break;
					case 21:
						{ return (new Yytoken(21, "tkn_tag_address", yytext(), yyline, yychar)); }
					case -22:
						break;
					case 22:
						{ return (new Yytoken(4, "tkn_tag_binding", yytext(), yyline, yychar)); }
					case -23:
						break;
					case 23:
						{ return (new Yytoken(3, "tkn_tag_portType", yytext(), yyline, yychar)); }
					case -24:
						break;
					case 24:
						{ return (new Yytoken(16, "tkn_tag_operation", yytext(), yyline, yychar)); }
					case -25:
						break;
					case 25:
						{ return (new Yytoken(11, "tkn_tag_definitions", yytext(), yyline, yychar)); }
					case -26:
						break;
					case 26:
						{ return (new Yytoken(20, "tkn_tag_documentation", yytext(), yyline, yychar)); }
					case -27:
						break;
					case 27:
						{ return (new Yytoken(1010, "tkn_sign_uri", yytext(), yyline, yychar)); }
					case -28:
						break;
					case 28:
						{ return (new Yytoken(102, "tkn_attr_targetNamespace", yytext(), yyline, yychar)); }
					case -29:
						break;
					case 30:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -30:
						break;
					case 31:
						{ return (new Yytoken(1010, "tkn_sign_uri", yytext(), yyline, yychar)); }
					case -31:
						break;
					case 33:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -32:
						break;
					case 35:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -33:
						break;
					case 37:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -34:
						break;
					case 39:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -35:
						break;
					case 41:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -36:
						break;
					case 43:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -37:
						break;
					case 45:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -38:
						break;
					case 47:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -39:
						break;
					case 49:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -40:
						break;
					case 51:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -41:
						break;
					case 53:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -42:
						break;
					case 55:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -43:
						break;
					case 57:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -44:
						break;
					case 59:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -45:
						break;
					case 61:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -46:
						break;
					case 62:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -47:
						break;
					case 63:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -48:
						break;
					case 64:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -49:
						break;
					case 68:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -50:
						break;
					case 69:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -51:
						break;
					case 70:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -52:
						break;
					case 71:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -53:
						break;
					case 72:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -54:
						break;
					case 73:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -55:
						break;
					case 74:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -56:
						break;
					case 75:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -57:
						break;
					case 76:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -58:
						break;
					case 77:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -59:
						break;
					case 78:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -60:
						break;
					case 79:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -61:
						break;
					case 80:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -62:
						break;
					case 81:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -63:
						break;
					case 82:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -64:
						break;
					case 83:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -65:
						break;
					case 84:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -66:
						break;
					case 86:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -67:
						break;
					case 88:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -68:
						break;
					case 89:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -69:
						break;
					case 90:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -70:
						break;
					case 91:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -71:
						break;
					case 92:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -72:
						break;
					case 93:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -73:
						break;
					case 94:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -74:
						break;
					case 95:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -75:
						break;
					case 96:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -76:
						break;
					case 97:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -77:
						break;
					case 98:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -78:
						break;
					case 99:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -79:
						break;
					case 100:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -80:
						break;
					case 101:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -81:
						break;
					case 102:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -82:
						break;
					case 103:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -83:
						break;
					case 104:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -84:
						break;
					case 105:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -85:
						break;
					case 106:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -86:
						break;
					case 107:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -87:
						break;
					case 108:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -88:
						break;
					case 109:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -89:
						break;
					case 110:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -90:
						break;
					case 111:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -91:
						break;
					case 112:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -92:
						break;
					case 113:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -93:
						break;
					case 114:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -94:
						break;
					case 115:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -95:
						break;
					case 116:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -96:
						break;
					case 117:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -97:
						break;
					case 118:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -98:
						break;
					case 119:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -99:
						break;
					case 120:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -100:
						break;
					case 121:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -101:
						break;
					case 122:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -102:
						break;
					case 123:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -103:
						break;
					case 124:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -104:
						break;
					case 125:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -105:
						break;
					case 126:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -106:
						break;
					case 127:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -107:
						break;
					case 128:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -108:
						break;
					case 129:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -109:
						break;
					case 130:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -110:
						break;
					case 131:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -111:
						break;
					case 132:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -112:
						break;
					case 133:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -113:
						break;
					case 134:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -114:
						break;
					case 135:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -115:
						break;
					case 137:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -116:
						break;
					case 138:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -117:
						break;
					case 139:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -118:
						break;
					case 140:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -119:
						break;
					case 141:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -120:
						break;
					case 142:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -121:
						break;
					case 143:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -122:
						break;
					case 144:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -123:
						break;
					case 145:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -124:
						break;
					case 146:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -125:
						break;
					case 147:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -126:
						break;
					case 148:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -127:
						break;
					case 149:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -128:
						break;
					case 150:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -129:
						break;
					case 151:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -130:
						break;
					case 152:
						{ return (new Yytoken(1001, "tkn_val_alfaNum", yytext(), yyline, yychar)); }
					case -131:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}