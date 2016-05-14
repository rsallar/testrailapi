package com.github.rsallar.testrailapi.sender;
public enum Status {
		
		FAILED(5),PASSED(1);
		private int status;
		Status(int status){
			this.status = status;
		}
				
		public int getStatus(){
			return status;
		}
	}