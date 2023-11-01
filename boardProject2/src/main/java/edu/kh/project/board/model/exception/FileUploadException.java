package edu.kh.project.board.model.exception;


// unchecked exception : 예외처리 선택(RuntimeException) 
// checked exception : 예외처리 필수 

public class FileUploadException extends RuntimeException{
	
	public FileUploadException() {
		
		super("파일 업로드 중 예외 발생");
		
	}
	
	public FileUploadException(String message) {
		super(message);
	}

}
