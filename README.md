# ForumSpring
Project Forum in Spring

Simple forum with CRUD operations.

Tools: Spring Framework, Hibernate, Thymeleaf, mySQL, IntelliJ Idea

<b>Functions:</b>

For everyone:
- /registration path for register new user
- /login path for sign in to forum

For authenticated (user):
- Home page with Last Activity (last inscription), top 5 new topics (/home)
- Section with topic list(10 for page) (/topic)
- Creating topic option with title and text (/newTopic)
- Searching option for looking for topics by word key (with regular expression) (/search)
- Find user option for looking for users (display information about topic and inscription numbers, display topic/inscription list) (/user)
- Creating inscriptions in topics (/topic/id/inscription)
- Delete/edit own inscriptions
- Editing text in topic (/topic/edit/id)

For administrator (admin):
- Admin section(/admin)
- Displaying all users (/admin/getusers)
- Delete topic/inscription by id
- Displaying in every topic informations about topic and inscription id
- Disable/Enable user


<b> Configuration </b>

    /* Database connection */

    spring.datasource.url=jdbc:mysql://localhost:3306/forumspring?serverTimezone=Europe/Warsaw&useLegacyDatetimeCode=true
    spring.datasource.username=admin
    spring.datasource.password=admin

    /* Hibernate */

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

    logging.level.org.hibernate.SQL=DEBUG
    logging.level.org.hibernate.type=TRACE

--------------------------

Path to class: src/main/java/pl/orlowski/sebastian/forumspring/config/SetupDataLoader.java
This class is for load basic user and roles to database.

    @Component
    public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private UserService userService;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SetupDataLoader(UserService userService, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        createRoleIfNotFound("USER");
        createRoleIfNotFound("ADMIN");

        if (userService.findByLogin("test") == null) {
            UserRegistrationDto user = new UserRegistrationDto();
            user.setLogin("test");
            user.setPassword(passwordEncoder.encode("test"));
            user.setEmail("test@test.com");
            userService.save(user);
        }

        alreadySetup = true;
    }

    @Transactional
    Role createRoleIfNotFound(String name) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
}



