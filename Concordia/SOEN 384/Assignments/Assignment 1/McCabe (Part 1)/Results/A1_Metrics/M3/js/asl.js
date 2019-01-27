var ASLHIGHLIGHTPOS = 7;
var ASLHIGHLIGHTCHAR = '|';
var FREQUENCYPOS = 0;
var FREQUENCYCHARS = "[F]";

function getParam(paramName) {
  var url = window.location.toString();
  var queryStr = url.split("?");
  var params = queryStr[1].split("&");
  for ( var i = 0; i < params.length; i++ ) {
    var paramItem = params[i].split("=");
    if ( paramItem[0] == paramName ) {
      return unescape(paramItem[1]);
    }
  }
  return null;
}

function uglyHackForIE() {
  if ( window.location.protocol == "file:" && window.ActiveXObject ) {
    Ajax.getTransport = function() { return new ActiveXObject("Microsoft.XMLHTTP"); }
  }
}

function loadListing() {
  uglyHackForIE();
  var title = getParam("title");
  if (title != null) {
    document.title = title;
  }
  var listingFile = getParam("listingFile");
  new Ajax.Request(listingFile, {
    onSuccess: function(response) {
      var lines = response.responseText.split("\n");
      var contents = "<pre>";
      for ( var i = 0; i < lines.length; i++ ) {
        var escapedLine = lines[i].escapeHTML();
        if ( escapedLine.length > ASLHIGHLIGHTPOS
             && escapedLine.substring( 0, FREQUENCYCHARS.length ) == FREQUENCYCHARS ) {
          contents += ("<span class=\"freq_highlight\">" + escapedLine + "</span>");
        } else if ( escapedLine.length > ASLHIGHLIGHTPOS
             && escapedLine.charAt(ASLHIGHLIGHTPOS) == ASLHIGHLIGHTCHAR ) {
          contents += ("<span class=\"asl_highlight\">" + escapedLine + "</span>");
        } else {
          contents += escapedLine;
        }
        contents += "\n";
      }
      contents += "</pre>";
      $("listing").update(contents);
    }
  });
}
