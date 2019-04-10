# Auth Support

## Usage
### Step 1
Add `@EnableAuth` on the starter class
```java
@SpringBootApplication
@EnableAuth
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### Step 2
Add `@AuthRequired` on the controller class or method  
Action Scope:
- Method: The special method will enable auth check
- Class: All methods in the class will enable auth check
```java
@RestController
@RequestMapping("/test")
@AuthRequired
public class DemoCtrl {
}
```

### Step 3
Add `@AuthUser User user` into method parameters
```java_holder_method_tree
@GetMapping("/ping")
public JsonResule ping(@AuthUser User user) {
    log.info("user: {}", user)
    return JsonResult.ok("pong");
}
```
