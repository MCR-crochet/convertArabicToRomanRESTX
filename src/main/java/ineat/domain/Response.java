package ineat.domain;

public class Response {
	private String codeRetour;
    private String message;

    public String getResponse() {
        return message;
    }

    public Response setResponse(final String message) {
    	this.codeRetour = "0";
        this.message = message;
        return this;
    }
    
    public Response setResponse(final String message, String codeRetour) {
    	this.codeRetour = codeRetour;
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                '}';
    }

	public String getCodeRetour() {
		return codeRetour;
	}

	public void setCodeRetour(String codeRetour) {
		this.codeRetour = codeRetour;
	}
}
