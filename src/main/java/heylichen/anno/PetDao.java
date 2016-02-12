package heylichen.anno;

import org.apache.ibatis.annotations.Insert;

/**
 * Created by lc on 2016/2/12.
 */
public interface PetDao {
    @Insert("INSERT INTO pet (\n" +
            "\tid,\n" +
            "\tNAME,\n" +
            "\tOWNER,\n" +
            "\tspecies,\n" +
            "\tsex,\n" +
            "\tbirth,\n" +
            "\tdeath\n" +
            ")\n" +
            "VALUES\n" +
            "\t(\n" +
            "\t\t#{id},\n" +
            "\t\t#{name},\n" +
            "\t\t#{owner},\n" +
            "\t\t#{species},\n" +
            "\t\t#{sex},\n" +
            "\t\t#{birth},\n" +
            "\t\t#{death}\n" +
            "\t)")
    void savePet(Pet pet);
}
