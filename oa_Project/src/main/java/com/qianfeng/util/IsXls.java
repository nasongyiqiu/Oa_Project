package com.qianfeng.util;

public class IsXls {
	// .xls .XLS .xlsx .XLSX
		// aaa.xls  ddd.XLSX
		public static boolean isXls(String fileName){
			//   js /^.+\.(xls)$/i
			// (?i) 右边的内容，忽略大小写
			if(fileName.matches("^.+\\.(?i)(xls)$")){
				return true;
			}else if(fileName.matches("^.+\\.(?i)(xlsx)$")){
				return false;
			}else{
				throw new RuntimeException("文件格式不对");
			}
			//Pattern p = Pattern.compile("^.+\\.(xls)$", )
			
		}

}
