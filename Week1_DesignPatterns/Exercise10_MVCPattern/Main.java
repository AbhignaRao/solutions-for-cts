public class Main {
    public static void main(String[] args) {

        // create model
        Student student = new Student("S001", "Guna", "A");

        // create view
        StudentView view = new StudentView();

        // create controller
        StudentController controller = new StudentController(student, view);

        // display initial details
        System.out.println("-- Initial Details --");
        controller.updateView();

        // update details via controller
        controller.setStudentName("Guna Byraju");
        controller.setStudentGrade("A+");

        // display updated details
        System.out.println("\n-- Updated Details --");
        controller.updateView();
    }
}