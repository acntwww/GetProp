Why there is a so simple project?
----

### What for?
I have worked for a lot of android projects, some is very kindly for building(Yes, I mean the building time is short!) and and some make me feeling sad when just waiting for the building....     

In normal developing day, I write logic code and run and test it on real device, while the business logic often has more than just one branch. So I usually write all the logic all at once and test on real device which really make my life happy!!     

In order to test efficiently I creat a util class making test without rebuilding the big project.


### How to?
Yes simply as below !! 
  
```java
    private void doSomething() {
        String condition = SystemProperties.read("haha");
        if ("success".equalsIgnoreCase(condition)) {
            onSuccess();
        } else {
            onFailed();
        }
        showResult(condition);
    }
```    

Change the condition through this command    

```shell
adb shell setprop log.tag.haha success
```


### And Why?
This class is copied and pasted every time, so here is the open source project, ~~and push to maven center~~.
