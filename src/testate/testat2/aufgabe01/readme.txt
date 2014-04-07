Aufgabe 1 a)

Aufgrund der fehlenden Sichtbarkeit und nicht gegebener Reihenfolge aufgrund des Weak Cache Consistency können sich die Threads gegenseitig blockieren.
Mit volatile kann jedoch die Sichtbarkeit garantiert werden und das Problem wird dadurch behoben.
(.Net braucht im Code noch eine Thread.MemoryBarrier(); vor den while Schlaufen, damit garantiert wird, dass diese erst nach den Wertzuweisungen ausgeführt werden.