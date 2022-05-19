
# GraphX
Graphx ist ein Kommandozeilen Programm zur Berechnung und Darstellung von Teilbereichen der Graphentheorie. Das Tool wurde ausschließlich in Java 17 geschrieben.

## Vorraussetzungen
Unabhängig von der verwendeten IDE muss die Java Library com.opencsv.CSVReader importiert werden.

Die Berechnungen finden auf Basis einer Adjazenzmatrix im CSV-Format statt für welche folgendes gelten muss.

![image](https://user-images.githubusercontent.com/50578549/160783519-48cf499a-1625-4894-a211-277163885511.png)


## Berechnungen
* [Distanzmatrix](#Distanzmatrix)
* [Exzentrizitäten](#Exzentrizitäten)
* [Radius](#Radius)
* [Durchmesser](#Radius)
* [Zentrum](#Zentrum)
* [Komponenten](#Komponenten)
* [Artikulationen](#Artikulationen)
* [Brücken](#Brücken)

### Distanzmatrix
![image](https://user-images.githubusercontent.com/50578549/160784109-d81253ef-fca2-4789-bd5d-677f644096f6.png)

### Exzentrizitäten
![image](https://user-images.githubusercontent.com/50578549/160784893-7344430e-8f95-475b-82d5-934baf2eb9bb.png)

Mithilfe der Exzentrizitäten des ungerichteten Graphen können Durchmesser und Radius wie folgt berechnet werden.

![image](https://user-images.githubusercontent.com/50578549/160786705-0f30deea-67a0-4ca4-a56e-f146cc3cb37e.png)

### Radius
<img width="536" alt="image" src="https://user-images.githubusercontent.com/50578549/169308561-44c7b88b-198f-4d95-a2cb-839b28822983.png">

### Durchmesser
<img width="492" alt="image" src="https://user-images.githubusercontent.com/50578549/169308602-a501a1de-1637-417e-a52a-1a2476e90a02.png">

### Zentrum
<img width="538" alt="image" src="https://user-images.githubusercontent.com/50578549/169308655-cb1e46a3-c0a5-451d-a507-835df42afdf0.png">

### Komponenten
<img width="328" alt="image" src="https://user-images.githubusercontent.com/50578549/169308837-892d55dd-d41c-4c51-b0d2-e221a18726f5.png">

### Artikulationen
<img width="332" alt="image" src="https://user-images.githubusercontent.com/50578549/169308891-b0820211-3929-43db-a1ff-cf22b9e06f98.png">

### Brücken
<img width="332" alt="image" src="https://user-images.githubusercontent.com/50578549/169308963-66b29aef-1e9e-4bc1-86d5-bb41218db1e3.png">
