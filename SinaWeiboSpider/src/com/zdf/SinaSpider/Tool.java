package com.zdf.SinaSpider;

public class Tool {
	private static boolean isNotEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 * 
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		int len = source.length();
		StringBuilder buf = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (isNotEmojiCharacter(codePoint)) {
				buf.append(codePoint);
			} else {

				buf.append("");

			}
		}
		return buf.toString();
	}
	
	public static String huoqucomment(String string) {
		String s = filterEmoji(string);
		
		
		String end="";
		if(s.contains("赞[")){
			if(s.contains("@")){
				if(s.indexOf("@")-s.indexOf(":")>1){
					end=s.substring(s.indexOf(":")+1, s.indexOf("@"));
				}else{
					int abc=s.indexOf(" ", s.lastIndexOf("@"))+1;
					end=s.substring(abc+1, s.indexOf("赞["));
				}
			}else if(s.contains("[组图")){
				end=s.substring(s.indexOf(":")+1, s.indexOf("[组图"));
			}else if(s.contains("原图 赞[")){
				end=s.substring(s.indexOf(":")+1, s.indexOf("原图 赞["));
			}else{
				end=s.substring(s.indexOf(":")+1, s.indexOf("赞["));
			}
		}else{
			end="";
		}
		
		return end;
		
	}
	}
