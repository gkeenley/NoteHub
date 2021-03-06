<!-----------------------------------------------------------------------------
    @author Paul Li
    @date Feb, 27, 2017
    @version 2.0 (Stage 2)

    Class Page (partial)
    ====================================
    Stage 2 Class page only has the note posts done.
 ------------------------------------------------------------------------------->

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title> NoteHub - Class Page</title>
</head>
<body>

<div ng-controller="classController" ng-cloak>

    <!-- TODO: Link it to create something -->
    <a class="btn btn-default btn-back" href="/home">
        <i class="fa fa-chevron-left" aria-hidden="true"></i>
        <span>BACK</span>
    </a>

    <h1>{{title}}</h1> <!-- TODO: controller for this page -->

    <!-- Grid System -->
    <div class="row" style="margin-left: auto; margin-right: auto; border-radius: 20px; box-shadow: 8px -8px 0 rgba(32, 152, 209, 0.6); background-color: rgba(32, 152, 209, 0.3)">

        <!-- a list of all the notes in this circle -->
        <div class="col-md-6">
            <note-list></note-list>
        </div>


        <!-- a list of all the sub-circles in this circle (Not for stage2) -->
        <div class="col-md-6">
            <circle-list></circle-list>
        </div>


    </div> <!-- /.row -->
</div> <!-- /.ng-app -->

</body>
</html>