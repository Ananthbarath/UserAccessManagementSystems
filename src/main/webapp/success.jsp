<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Request Submitted Successfully</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            background-color: #f0f0f0;
        }
        .container {
            text-align: center;
            padding: 30px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        .message {
            font-size: 18px;
            color: #333;
            margin-bottom: 20px;
        }
        .button {
            text-decoration: none;
            padding: 10px 20px;
            color: #fff;
            background-color: #4CAF50;
            border-radius: 5px;
            font-size: 16px;
        }
        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Success!</h2>
        <p class="message">Your access request has been submitted successfully.</p>
        <a href="requestAccess.jsp" class="button">Submit Another Request</a>
        <a href="home.jsp" class="button">Go to Home</a>
    </div>
</body>
</html>
