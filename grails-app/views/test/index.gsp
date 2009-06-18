<html>
<head>
    <esp:resource type="js"/>
    <esp:resource type="css"/>
</head>
<body>
    <p>This is a test page. It should have some CSS and show an alert, without that code in the HTML. I should be pink.</p>
    
    <esp:store type="js">
        window.alert('Holy shit it works')
    </esp:store>
    <esp:store type="css">
        p { color: pink; font-size: 40px }
    </esp:store>
    
</body>
</html>
