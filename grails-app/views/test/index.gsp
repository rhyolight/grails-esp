<html>
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js" type="text/javascript"></script>
    			<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js" type="text/javascript"></script>
    <esp:resource type="js"/>
    <esp:resource type="css"/>
</head>
<body>
    <p>This is a test page. It should have some CSS and show an alert, without that code in the HTML. I should be pink.</p>
    
    <esp:store type="js">
        window.alert('Holy shit it works');
    </esp:store>
    <esp:store type="css">
        p { color: pink; font-size: 40px }
    </esp:store>
    
    <ul id='sortme'>
        <li>one</li>
        <li>two</li>
        <li>three</li>
    </ul>
    
    
</body>
</html>
