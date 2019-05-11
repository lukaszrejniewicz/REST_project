package pl.zagorski.FootballDataRest.dto;

public class ErrorDto {
    private String message;
    private String event;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
