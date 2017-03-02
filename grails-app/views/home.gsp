<!-----------------------------------------------------------------------------
    @author Paul Li
    @date Feb, 27, 2017
    @version 2.0 (Stage 2)

    Home Page (partial)
    ====================================
    Stage 2 Home page only has the users circles
 ------------------------------------------------------------------------------->

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title> NoteHub - Home Page</title>
</head>


<body>

<div ng-app="notehub">
    <!-- TODO: Link it to create post-->
    <a class="btn btn-default btn-back" href="/home">
        <i class="fa fa-chevron-left" aria-hidden="true"></i>
        <span>BACK</span>
    </a>

    <!-- Page Title --> <!-- TODO: controller for the page-->
    <h1>Home</h1>

    <!-- Grid System -->
    <div class="row">

        <!-- User's Circles -->
        <div class="col-md-6">
            <h2>Your Circles</h2>
            <circles></circles>
        </div> <!-- /.col1/1-->

        <!-- Circle News -->
        <div class="col-md-6">
            <h2>Circle News</h2>
            {{TODOBOX}}
        </div> <!-- /.col2/2 -->
    </div> <!-- /.row -->
</div> <!-- /.ng-app: noteHub -->


</body>
</html>