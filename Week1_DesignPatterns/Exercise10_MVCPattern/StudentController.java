// Controller
public class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name) { model.setName(name); }
    public void setStudentGrade(String grade) { model.setGrade(grade); }
    public void setStudentId(String id) { model.setId(id); }

    public String getStudentName() { return model.getName(); }
    public String getStudentGrade() { return model.getGrade(); }
    public String getStudentId() { return model.getId(); }

    public void updateView() {
        view.displayStudentDetails(
            model.getId(),
            model.getName(),
            model.getGrade()
        );
    }
}