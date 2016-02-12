package heylichen.anno;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class RunApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        PetDao mapper = ctx.getBean(PetDao.class);
        Pet pet = new Pet();
        pet.setId(2);
        pet.setOwner("Jack");
        pet.setName("kitty");
        pet.setSex("1");
        pet.setSpecies("中华田园猫");
        pet.setBirth(new Date());
        pet.setDeath(new Date());
        mapper.savePet(pet);
        System.out.println("---Data saved---");
    }
} 