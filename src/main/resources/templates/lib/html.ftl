<#macro html>
    <!DOCTYPE html>
    <html lang="en">
    <#nested>
    </html>
</#macro>

<#macro head title>
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
        <#nested>
    </head>
</#macro>

<#macro body onload="">
    <body onload="${onload}">
    <#nested>
    </body>
</#macro>

