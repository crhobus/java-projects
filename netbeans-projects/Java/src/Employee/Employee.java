
package Employee;

public abstract class Employee {

    private String firstName;
    private String lastName;
    private String socialSecuritiNumber;

    public Employee(String first, String last, String ssn) {
        this.firstName = first;
        this.lastName = last;
        this.socialSecuritiNumber = ssn;
    }

    public void setFirstName(String first){
        this.firstName = first;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setLastName(String last){
        this.lastName = last;
    }

    public String getLastName(){
        return lastName;
    }

    public void setSocialSecuritiNumber(String ssn){
        this.socialSecuritiNumber = ssn;
    }

    public String getSocialSecuritiNumber(){
        return socialSecuritiNumber;
    }

    @Override
    public String toString(){
        return String.format("%s %s\nsocial seruriti number: %s", getFirstName(), getLastName(), getSocialSecuritiNumber());
    }

    public abstract double earnings();
}
