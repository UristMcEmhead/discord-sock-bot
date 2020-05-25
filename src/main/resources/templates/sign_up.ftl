<#import "lib/html.ftl" as H>
<#import "/spring.ftl" as spring>

<@H.html>
    <@H.head "Sign up">
    </@H.head>
    <@H.body>
        <form method="post" action="/user/signUp">
            <input type="text" placeholder="name" name="name"><br>
            <input type="password" placeholder="password" name ="password"><br>
            <input type="submit" value="Submit">
        </form>
    </@H.body>
</@H.html>