package com.nwpu.pet.entity;

public class Pet {
    private String petNum; // 编号
    private String name;
    private int age;
    private String gender;
    private String blood;
    private double price;
    private String category;// 类型

    public Pet() {
        this.petNum = Sequence.nextVal();
    }



    public Pet(String name, int age, String gender, String blood, double price, String category) {
        this.petNum = Sequence.nextVal();
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.blood = blood;
        this.price = price;
        this.category = category;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPetNum() {
        return petNum;
    }

    @Override
    public String toString() {
        return "Pet [petNum=" + petNum + ", name=" + name + ", age=" + age + ", gender=" + gender + ", blood=" + blood
                + ", price=" + price + ", category=" + category + "]";
    }

    /**
     * 自增长内部类，完成Pet编号的自增长生成。
     * 宠物编号按照： nwpu_1, nwpu_2,...的顺序自增长产生
     * @author nwpu
     *
     */
    private static class Sequence {
        final static String PREFIX = "nwpu_";// 编号前缀
        private static int initNum = 0;

        static String nextVal() {
            return PREFIX + (++initNum);
        }
    }
}
