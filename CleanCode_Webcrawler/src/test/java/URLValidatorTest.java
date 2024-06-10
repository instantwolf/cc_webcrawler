public class URLValidatorTest {


    /**
     * Mögliche Tests: Teste einmal alle typen von URLS die valide sein können
     * (mit ohne protokoll, jeweils einmal http|https)
     *
     *  Generelle URL Struktur:
     *  [Protokoll]{?} [www.]{?} ([subdomain].){?} [domain].[top-level-domain] (([/][URI]){?} | [/]{?}) ([?][URL-Params]){?}
     *
     *  {?} ... Optionale Stelligkeit -> 0 oder 1
     *
     * Protokoll: zulässig für uns http|https
     *
     *  www: glaub es gibt auch www2 etc. aber für uns eigtl. wurscht?
     *
     *  subdomain .. egal einfach irgendeine Zeichenkette .. (Vorhandensein kann anhand der Punkte festgestellt werden)
     *
     *  domain -> irgendeine Zeichenkette
     *
     *  top-level-domain (normalerweise 2 zeichen iso code.. oft aber mehr zeichen (com / info / org)
     *
     *  URI: Wenn vorhanden immer mit schrägstrich als prefix.
     *  Schrägstrich kann aber auch ohne URI am Ende der URL vorkommen (optional)
     *
     *  URL-Params: Unterstützen wir einfach nicht.. sonst wirds zu kompliziert..
     *  daher auch kein Fragezeichen am Ende der URL|des URI strings
     */
}
