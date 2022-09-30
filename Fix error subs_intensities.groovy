/// Script by Peter Bankhead

/// When running intensity features on tiles misses some tiles from analysis. Run and redo intensity features

def toRemove = getAllObjects().findAll {it.hasROI() && (it.getROI().getBoundsWidth() <= 0 || it.getROI().getBoundsHeight() <= 0)}

removeObjects(toRemove, true)
