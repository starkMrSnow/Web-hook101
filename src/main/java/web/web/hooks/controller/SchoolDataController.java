package web.web.hooks.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import web.web.hooks.modal.SchoolData;
import web.web.hooks.repository.SchoolDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;


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
    
}
