package web.web.hooks.modal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class WebHookDetails {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    @Column(name = "ID")
    private Integer Id;
    
    private String eventName;
    private String endPointUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id")
    private SchoolData schoolData;

    public WebHookDetails() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEndPointUrl() {
        return endPointUrl;
    }

    public void setEndPointUrl(String endPointUrl) {
        this.endPointUrl = endPointUrl;
    }

    public SchoolData getSchoolData() {
        return schoolData;
    }

    public void setSchoolData(SchoolData schoolData) {
        this.schoolData = schoolData;
    }
    
}
