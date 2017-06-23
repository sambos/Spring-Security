<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
</head>

<body>
<H1> welcome page </H1>
<a href="<c:url value="welcome"/>"> Access unsecure page</a> <br>
 <a href="<c:url value="admin"/>">Access protected page</a>
</body>
</html>