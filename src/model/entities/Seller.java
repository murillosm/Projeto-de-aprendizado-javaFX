package model.entities;

import java.util.Date;
import java.util.Objects;

public class Seller {
    private Integer idSeller;
    private String nameSeller;
    private String email;
    private Date birthDate;
    private double baseSalary;

    private Department department;

    /***************** Constructor *******************/
    public Seller() {
    }
    public Seller(Integer idSeller, String nameSeller, String email, Date birthDate, double baseSalary, Department department) {
        this.idSeller = idSeller;
        this.nameSeller = nameSeller;
        this.email = email;
        this.birthDate = birthDate;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    /******************* Get and Set *******************/
    public Integer getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(Integer idSeller) {
        this.idSeller = idSeller;
    }

    public String getNameSeller() {
        return nameSeller;
    }

    public void setNameSeller(String nameSeller) {
        this.nameSeller = nameSeller;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return idSeller == seller.idSeller;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSeller);
    }

    @Override
    public String toString() {
        return "Seller{" +
               "idSeller = " + idSeller +
               ", nameSeller = '" + nameSeller + '\'' +
               ", email = '" + email + '\'' +
               ", birthDate = " + birthDate +
               ", baseSalary = " + baseSalary +
               ", " + department +
               '}';
    }
}
