This a Spring Boot project where I was trying to create Spring Book Security Jwt Redis Demo app to demonstrated Login and Logout,
but the project is failing to compile. I copied the project from the following Git Repository and made some minor changes.
However I dont understand why the project is failing to compile:

Original Github Source: https://github.com/JianChoi-Kor/Login

Ther errors I am facing are as below:

org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'authController' defined in file [C:\MyProjects\jwt\target\classes\com\quadrant\jwt\auth\AuthController.class]: Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'authService' defined in file [C:\MyProjects\jwt\target\classes\com\quadrant\jwt\auth\AuthService.class]: Unsatisfied dependency expressed through constructor parameter 1: Error creating bean with name 'webSecurityConfig' defined in file [C:\MyProjects\jwt\target\classes\com\quadrant\jwt\config\WebSecurityConfig.class]: Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'jwtTokenProvider' defined in file [C:\MyProjects\jwt\target\classes\com\quadrant\jwt\tokenutils\JwtTokenProvider.class]: Failed to instantiate [com.quadrant.jwt.tokenutils.JwtTokenProvider]: Constructor threw exception
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:798) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:237) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1354) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1191) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:561) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:521) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:325) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:323) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:975) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:959) ~[spring-context-6.1.3.jar:6.1.3]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:624) ~[spring-context-6.1.3.jar:6.1.3]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146) ~[spring-boot-3.2.2.jar:3.2.2]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:754) ~[spring-boot-3.2.2.jar:3.2.2]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:456) ~[spring-boot-3.2.2.jar:3.2.2]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:334) ~[spring-boot-3.2.2.jar:3.2.2]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1354) ~[spring-boot-3.2.2.jar:3.2.2]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1343) ~[spring-boot-3.2.2.jar:3.2.2]
	at com.quadrant.jwt.JwtApplication.main(JwtApplication.java:10) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:568) ~[na:na]
	at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:50) ~[spring-boot-devtools-3.2.2.jar:3.2.2]
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'authService' defined in file [C:\MyProjects\jwt\target\classes\com\quadrant\jwt\auth\AuthService.class]: Unsatisfied dependency expressed through constructor parameter 1: Error creating bean with name 'webSecurityConfig' defined in file [C:\MyProjects\jwt\target\classes\com\quadrant\jwt\config\WebSecurityConfig.class]: Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'jwtTokenProvider' defined in file [C:\MyProjects\jwt\target\classes\com\quadrant\jwt\tokenutils\JwtTokenProvider.class]: Failed to instantiate [com.quadrant.jwt.tokenutils.JwtTokenProvider]: Constructor threw exception
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:798) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:237) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1354) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1191) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:561) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:521) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:325) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:323) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1443) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1353) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:907) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:785) ~[spring-beans-6.1.3.jar:6.1.3]
	... 24 common frames omitted
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'webSecurityConfig' defined in file [C:\MyProjects\jwt\target\classes\com\quadrant\jwt\config\WebSecurityConfig.class]: Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'jwtTokenProvider' defined in file [C:\MyProjects\jwt\target\classes\com\quadrant\jwt\tokenutils\JwtTokenProvider.class]: Failed to instantiate [com.quadrant.jwt.tokenutils.JwtTokenProvider]: Constructor threw exception
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:798) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:237) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1354) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1191) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:561) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:521) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:325) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:323) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:409) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1334) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1164) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:561) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:521) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:325) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:323) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1443) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1353) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:907) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:785) ~[spring-beans-6.1.3.jar:6.1.3]
	... 38 common frames omitted
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'jwtTokenProvider' defined in file [C:\MyProjects\jwt\target\classes\com\quadrant\jwt\tokenutils\JwtTokenProvider.class]: Failed to instantiate [com.quadrant.jwt.tokenutils.JwtTokenProvider]: Constructor threw exception
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateBean(AbstractAutowireCapableBeanFactory.java:1316) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1201) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:561) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:521) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:325) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:323) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1443) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1353) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:907) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:785) ~[spring-beans-6.1.3.jar:6.1.3]
	... 61 common frames omitted
Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.quadrant.jwt.tokenutils.JwtTokenProvider]: Constructor threw exception
	at org.springframework.beans.BeanUtils.instantiateClass(BeanUtils.java:223) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:88) ~[spring-beans-6.1.3.jar:6.1.3]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateBean(AbstractAutowireCapableBeanFactory.java:1310) ~[spring-beans-6.1.3.jar:6.1.3]
	... 73 common frames omitted
Caused by: java.lang.IllegalArgumentException: Decode argument cannot be null.
	at io.jsonwebtoken.lang.Assert.notNull(Assert.java:94) ~[jjwt-api-0.12.3.jar:0.12.3]
	at io.jsonwebtoken.io.ExceptionPropagatingDecoder.decode(ExceptionPropagatingDecoder.java:50) ~[jjwt-api-0.12.3.jar:0.12.3]
	at com.quadrant.jwt.tokenutils.JwtTokenProvider.<init>(JwtTokenProvider.java:38) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) ~[na:na]
	at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:499) ~[na:na]
	at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:480) ~[na:na]
	at org.springframework.beans.BeanUtils.instantiateClass(BeanUtils.java:197) ~[spring-beans-6.1.3.jar:6.1.3]
	... 75 common frames omitted


Process finished with exit code 0
