package bean.example.beandemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component("user")
public class User {
    private String name;

    @Autowired
    private Random random;

//    @Autowired
//    @Qualifier("bike")
    private Vehicle vehicle;

//    @Autowired
//    public User( @Qualifier("bike") Vehicle vehicle) {
//        this.vehicle = vehicle;
//    }

    @Autowired
    public void setVehicle(@Qualifier("bus") Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void showVehicle(){
        vehicle.run();
    }

    public void sayHello(){
        System.out.println("Xin chao");
    }
    public void randomNumber(){
        System.out.println("so random " + random.nextInt(1000));
    }
}
