// Sets tabbertabdefault class attribute for tabs,
// based on the URL hash.
function activateProperTabs() {
  var attrName = (navigator.product == "Gecko") ? "class" : "className";
  var attrValue = "tabbertab tabbertabdefault";
  if ( location.hash ) {
    if ( location.hash == "#std" ) {
      var stdtab = document.getElementById("stdtab");
      stdtab.setAttribute(attrName, attrValue);
    }
    else {
      var covtab = document.getElementById("covtab");
      covtab.setAttribute(attrName, attrValue);
      if ( location.hash == "#line_cov" ) {
        var linecovtab = document.getElementById("linecovtab");
        linecovtab.setAttribute(attrName, attrValue);
      }
      else if ( location.hash == "#branch_cov" ) {
        var branchcovtab = document.getElementById("branchcovtab");
        branchcovtab.setAttribute(attrName, attrValue);
      }
      else if ( location.hash == "#path_cov" ) {
        var pathcovtab = document.getElementById("pathcovtab");
        pathcovtab.setAttribute(attrName, attrValue);
      }
    }
  }
}

function makeSortable(tableid, columns, initialSortColumn) {
  var theTable = document.getElementById(tableid);
  theTable.style.tableLayout = "fixed";
  if ( navigator.product == "Gecko" ) {
    // Mozilla needs this, but IE must not
    theTable.style.width = "100%";
  }
  var sortableTable = new SortableTable(theTable, columns);
  sortableTable.sort(initialSortColumn, true);
}


function drawriskpie(
  canvasid,         // Canvas to draw pie chart on
  coloredtableid,   // ID of risk table (red and yellow counted from here)
  numhighrisk,      // ID of table cell to populate with total reds (can be null)
  nummedrisk,       // ID of table cell to populate with total yellows (can be null)
  totalmods) {      // Total number of modules

  // Get risk table
  var riskTable = document.getElementById(coloredtableid);
  var redCount = 0;
  var yellowCount = 0;
  for ( var i = 0; i < riskTable.rows.length; i++ ) {
    if ( riskTable.rows[i].className == "red" ) {
      redCount++;
    }
    else if ( riskTable.rows[i].className == "yellow" ) {
      yellowCount++;
    }
  }

  // Calculate green
  var greenCount = totalmods - (redCount + yellowCount);

  // Create dataset
  var idx = 0;
  var dataArray = new Array();
  var colorArray = new Array();
  var labelArray = new Array();
  if ( redCount > 0 ) {
    dataArray.push([idx, redCount]);
    colorArray.push(Color.fromString("red"));
    labelArray.push({v:idx, label:'High'});
    idx++;
  }
  if ( yellowCount > 0 ) {
    dataArray.push([idx, yellowCount]);
    colorArray.push(Color.fromString("#fdd700"));
    labelArray.push({v:idx, label:'Med'});
    idx++;
  }
  if ( greenCount > 0 ) {
    dataArray.push([idx, greenCount]);
    colorArray.push(Color.fromString("#00b000"));
    labelArray.push({v:idx, label:'Low'});
    idx++;
  }
    
  if ( numhighrisk != null ) {
    var highcell = document.getElementById(numhighrisk);
    highcell.innerHTML = redCount;
  }
  if ( nummedrisk != null ) {
    var medcell = document.getElementById(nummedrisk);
    medcell.innerHTML = yellowCount;
  }
  
  // Options
  var dataOptions = {
    xTicks: labelArray
  };
  var bodyElement = document.getElementsByName("body")[0];
  var renderOptions = {
    backgroundColor: Color.fromBackground(bodyElement),
    colorScheme: colorArray
  };

  var layout = new PlotKit.Layout("pie", dataOptions);
  layout.addDataset("theDataSet", dataArray);
  layout.evaluate();
  
  var canvas = MochiKit.DOM.getElement(canvasid);
  var plotter = new PlotKit.SweetCanvasRenderer(canvas, layout, renderOptions);
  plotter.render();    
}


function drawlinehistogrambar(
  canvasid,      // ID of canvas to draw on
  ten,           // Number of modules with <= 10 lines
  twenty,        // Number of modules with 11-20 lines
  fifty,         // Number of modules with 21-50 lines
  onehundred,    // Number of modules with 51-100 lines
  twohundred,    // Number of modules with 101-200 lines
  fivehundred,   // Number of modules with 201-500 lines
  onethousand,   // Number of modules with 501-1000 lines
  others) {      // Number of modules with 1001+ lines

  var dataset = [
    [0, ten], 
    [1, twenty], 
    [2, fifty], 
    [3, onehundred], 
    [4, twohundred], 
    [5, fivehundred], 
    [6, onethousand],
    [7, others]
  ];

  var dataOptions = {
    yTickPrecision: 0,
    xTicks: [
      {v:0, label:'<=10'}, 
      {v:1, label:'11-20'}, 
      {v:2, label:'21-50'},
      {v:3, label:'51-100'},
      {v:4, label:'101-200'},
      {v:5, label:'201-500'},
      {v:6, label:'501-1000'},
      {v:7, label:'1001+'}
    ]
  };

  var renderOptions = {
  };

  // Somehow, if we don't set number of ticks when the values
  // are all below 5, the y axis does not render properly.
  var highest =
    Math.max(
      ten, Math.max(
        twenty, Math.max(
          fifty, Math.max(
            onehundred, Math.max(
              twohundred, Math.max(
                fivehundred, Math.max(
                  onethousand, others)))))));
  if ( highest < 5 ) {
    dataOptions.yNumberOfTicks = highest;
  }
 
  var layout = new PlotKit.Layout("bar", dataOptions);
  layout.addDataset("theDataset", dataset);
  layout.evaluate();
  
  var canvas = MochiKit.DOM.getElement(canvasid);
  var plotter = new PlotKit.SweetCanvasRenderer(canvas, layout, renderOptions);
  plotter.render();
}  

function drawtestbar(
  canvasid,    // ID of canvas to draw on
  line,        // Percentage lines tested
  branch,      // Percentage branches tested
  path) {      // Percentage path tested

  var dataset = [
    [0, line * 100], 
    [1, branch * 100], 
    [2, path * 100] 
  ];

  var dataOptions = {
    yAxis: [0, 100],
    xTicks: [
      {v:0, label:'Line'}, 
      {v:1, label:'Branch'}, 
      {v:2, label:'Path'}
    ]
  };

  var renderOptions = {
    colorScheme: [ Color.fromString("#008000") ]
  };

  var layout = new PlotKit.Layout("bar", dataOptions);
  layout.addDataset("theDataset", dataset);
  layout.evaluate();
  
  var canvas = MochiKit.DOM.getElement(canvasid);
  var plotter = new PlotKit.SweetCanvasRenderer(canvas, layout, renderOptions);
  plotter.render();
}

function getWindowSize() {
  var w = 0;
  var h = 0;
  if (typeof(window.innerWidth) == "number") {
    // Non-IE
    w = window.innerWidth;
    h = window.innerHeight;
  } else if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {
    // IE 6+ in 'standards compliant mode'
    w = document.documentElement.clientWidth;
    h = document.documentElement.clientHeight;
  } else if (document.body && (document.body.clientWidth || document.body.clientHeight)) {
    // IE 4 compatible
    w = document.body.clientWidth;
    h = document.body.clientHeight;
  }
  return { width: w, height: h };
}

function showAslLight(anchor) {
  var dim = getWindowSize();
  var iframe = Builder.node("iframe", { id: "MB_iframe", src: anchor.href });
  Modalbox.show(iframe, { 
    title: "Annotated Source Listing", 
    width: dim.width * 0.9, 
    height: dim.height * 0.9, 
    transitions: false 
  });
}

function showAslHeavy(anchor) {
  var dim = getWindowSize();
  var features = "status=1,toolbar=0,menubar=0,location=0,scrollbars=1, resizable=1";
  features += ",width=" + dim.width * 0.9 + ",height=" + dim.height * 0.9;
  window.open(anchor.href + "&title=" + anchor.innerHTML.escapeHTML(), "_blank", features);
}

function modalifyAslAnchors() {
  $$("a[rel='asl']").each(function(e) {
    e.observe("click", function(event) {
      showAslLight(event.element());
      event.stop();
    });
  });
  new Proto.Menu({
    selector: "a[rel='asl']",
    className: "menu firefox",
    menuItems: [
      { name: "ASL...", callback: function(event) { showAslLight(event.element()); } },
      { name: "ASL in new window...", callback: function(event) { showAslHeavy(event.element()); } }
    ]
  });
}
