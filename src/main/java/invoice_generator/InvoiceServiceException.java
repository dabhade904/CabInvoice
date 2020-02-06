package invoice_generator;

public class InvoiceServiceException extends Exception {

      ExceptionType type;

    public  InvoiceServiceException(String message,String name){}
     public  enum ExceptionType{
            INVALID_USER_ID,
    }

    public InvoiceServiceException(String message,ExceptionType type){
        super(message);
        this.type=type;
    }


}
