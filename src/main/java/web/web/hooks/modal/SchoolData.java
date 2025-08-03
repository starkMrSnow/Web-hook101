package web.web.hooks.modal;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class SchoolData {
   
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
     @Column(name = "ID")
    private Long schoolId;

    private String schoolName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schoolData", cascade = CascadeType.ALL)
    private List<WebHookDetails> webHookDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schoolData", cascade = CascadeType.ALL)
    private List<Student> students;

    public String getSchoolName() {
        return schoolName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public List<WebHookDetails> getWebHookDetails() {
        return webHookDetails;
    }

    public void setWebHookDetails(List<WebHookDetails> webHookDetails) {
        this.webHookDetails = webHookDetails;
    }

    
}
