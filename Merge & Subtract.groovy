
/// Original script from Github, modified by Sara Harrizi
/// Merging multiple annotations and subtracting them from another annotation.

/// Merging Portal Zones

selectObjects {it.isAnnotation() && it.getPathClass() == getPathClass("Portal Zone")}
mergeSelectedAnnotations()

/// Subtracting Portal Zones from Outer Margins

def OuterMargin = getAnnotationObjects().find {it.getPathClass() == getPathClass("OuterMargin")}
def PZ = getAnnotationObjects().find {it.getPathClass() == getPathClass("Portal Zone")}

def g1 = OuterMargin.getROI().getGeometry()
def g2 = PZ.getROI().getGeometry()

def plane = OuterMargin.getROI().getImagePlane()
if (plane != PZ.getROI().getImagePlane()) {
    println 'Annotations are on different planes!'
    return    
}

def difference = g1.difference(g2)

/*if (difference.isEmpty())
    println "No intersection between areas"
else {**/
    def roi = GeometryTools.geometryToROI(difference, plane)
    def annotation = PathObjects.createAnnotationObject(roi, getPathClass('OuterMargiN'))
    addObject(annotation)
    //selectObjects(annotation)**/
    removeObject(OuterMargin, true)
    //removeObject(PZ, true)
    println "Outer MargiN created"


/// Subtracting Portal Zones from Outer Margins

def InnerMargin = getAnnotationObjects().find {it.getPathClass() == getPathClass("Invasion front")}

def g3 = InnerMargin.getROI().getGeometry()

def planeNew = InnerMargin.getROI().getImagePlane()
if (planeNew != PZ.getROI().getImagePlane()) {
    println 'Annotations are on different planes!'
    return    
}

def differenceNew = g3.difference(g2)

/*if (difference.isEmpty())
    println "No intersection between areas"
else {**/
    def roiNew = GeometryTools.geometryToROI(differenceNew, planeNew)
    def annotationNew = PathObjects.createAnnotationObject(roiNew, getPathClass('Invasion Front'))
    addObject(annotationNew)
    //selectObjects(annotation)**/
    removeObject(InnerMargin, true)
    removeObject(PZ, true)
    println "Invasion Front created"
    
/*selectObjects {it.isAnnotation() && it.getPathClass() == getPathClass("OuterMargin")}
setPathClass("OuterMargiN")**/
