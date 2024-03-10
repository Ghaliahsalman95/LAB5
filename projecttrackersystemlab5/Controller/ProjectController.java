package com.example.projecttrackersystemlab5.Controller;

import com.example.projecttrackersystemlab5.APIResponse.APIResponse;
import com.example.projecttrackersystemlab5.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    /* Create project tracker system , Where you can get all the project ,
add , remove , update project.
project Class : ID , title , description , status, companyName
• Create a new project (ID,title , description , status, companyName)
• Display all project .
• Update a project
• Delete a project
• Change the project status as done or not done
• Search for a project by given title
• Display All project for one company by companyName*/


    //• Create a new project (ID,title , description , status, companyName)
    ArrayList<Project> projects = new ArrayList<Project>();

    @PostMapping("/addProject")
    public APIResponse addProject(@RequestBody Project project) {
        projects.add(project);
        return new APIResponse("Project " + project.getTitle() + " Added successfully");

    }
    //• Display all project .
    @GetMapping("getProjects")
    public ArrayList<Project>getAllProject(){
        return projects;
    }
    //• Update a project
    @PutMapping("/updated/{index}")
    public APIResponse updateProject(@PathVariable int index,@RequestBody Project project){
        if (index<projects.size()){
            projects.set(index,project);
            return new APIResponse("Project is updated successfully");
        }return new APIResponse("INDEX NOT VALID");

    }
    //• Delete a project
    @DeleteMapping("/delete/{index}")
    public APIResponse delete(@PathVariable int index){
        if (index<projects.size()){
            String info=projects.get(index).getTitle();
            projects.remove(index);
            return new APIResponse("Project "+info+ "is deleted successfully");

        }return new APIResponse("INDEX NOT VALID");
    }
    //• Change the project status as done or not done
    @PutMapping("/change/{index}")
    public APIResponse change(@PathVariable int index){
        if (index<projects.size()){
            if (projects.get(index).getStatus().equalsIgnoreCase("DONE"))
            {//projects.get(index).setStatus("NOT DONE");
            return new APIResponse("Project "+projects.get(index).getTitle()+" is DONE already");}
            else {projects.get(index).setStatus("DONE");
                return new APIResponse("Change Project "+projects.get(index).getTitle()+" with DONE");}

        }else return new APIResponse("INDEX NOT VALID");}
@GetMapping("/search/{title}")
    public Project searchProject(@PathVariable String title){
        title=title.replace(" ","");
    for (Project project:projects){
        if (project.getTitle().equalsIgnoreCase(title))
            return project;
    }
    return null;
    }


    //• Display All project for one company by companyName.
    @GetMapping("/displayProjectCompany/{name}")
    public ArrayList<Project>displayProjectsCompany(@PathVariable String name){
        ArrayList<Project>projectsCompany=new ArrayList<>();
for (Project project:projects){
    if (project.getCompanyName().equalsIgnoreCase(name)){
        projectsCompany.add(project);
    }
}
    return projectsCompany;}

}

