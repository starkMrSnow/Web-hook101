package web.web.hooks.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import web.web.hooks.modal.SchoolData;
import web.web.hooks.modal.Student;
import web.web.hooks.modal.WebHookDetails;
import web.web.hooks.repository.SchoolDataRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/school")
public class SchoolDataController {
    
    @Autowired
    public SchoolDataRepository schoolDataRepository;
    
    @PostMapping("/addNewSchool")
    public @ResponseBody SchoolData addNewSchool (@RequestParam String schoolName) {
        SchoolData schoolData = new SchoolData();
        schoolData.setSchoolName(schoolName);
        schoolDataRepository.save(schoolData);
        
        return schoolData;
    }
    
     @PostMapping("/addWebHookEvent/{schoolId}")
    public @ResponseBody String addWebHookEvent (@PathVariable Long schoolId, @RequestBody WebHookDetails webHookDetails) {
      Optional<SchoolData> schoolData = schoolDataRepository.findById(schoolId);
        
     List<WebHookDetails> webHookDetailsList = new ArrayList<>();
     WebHookDetails newWebHookDetails = new WebHookDetails();
        newWebHookDetails.setEventName(webHookDetails.getEventName() );
        newWebHookDetails.setEndPointUrl(webHookDetails.getEndPointUrl());

        newWebHookDetails.setSchoolData(schoolData.get());
        webHookDetailsList.add(newWebHookDetails);

        schoolData.get().setWebHookDetails(webHookDetailsList);
        schoolDataRepository.save(schoolData.get());
        return "web hook added";
}

    @PostMapping("/addStudent/{schoolId}")
    public @ResponseBody String addStudent (@PathVariable Long schoolId, @RequestBody Student reqStudent) {
      Optional<SchoolData> schoolData = schoolDataRepository.findById(schoolId);
      SchoolData  schoolData2 = schoolData.get(); 

      List<Student> student = new ArrayList<>();
      Student Student  = new Student();
      Student.setName(reqStudent.getName());
      Student.setAge(reqStudent.getAge());
      Student.setSchoolData(schoolData2);
      student.add(Student);
    
      schoolData2.setStudents(student);
        schoolDataRepository.save(schoolData2);

        // send data as webhook here
        WebHookDetails webHookDetails = schoolData2.getWebHookDetails()
                .stream()
                .filter(eventData -> eventData.getEventName().equals("add"))
                .findFirst()
                .orElse(null);
        if (webHookDetails != null && webHookDetails.getEndPointUrl() != null) {
            String url = webHookDetails.getEndPointUrl(); //localhost:9000
            url += "/"+reqStudent.getName();
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url, String.class);
            System.out.println("Result" + result);
        }
        return "Student added";
}
}