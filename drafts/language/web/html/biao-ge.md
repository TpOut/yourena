# 表格

最好还是不要使用嵌套表格，会降低可访问性。

```markup
<table>
    <colgroup>
        <col style="background-color: yellow">
    </colgroup>
    <caption>Dinosaurs in the Jurassic period</caption>

    <!-- scope和id都可以用来准确标识行列，大部分情况下用scope就行，id实在太麻烦 -->
    <thead>
        <tr>
            <th scope="col">&nbsp;</th>
            <th scope="colgroup" colspan="4">Ella</th>
        </tr>
        <tr>
            <th scope="col">&nbsp;</th>
            <th scope="col">Knocky</th>
            <th scope="col">Flor</th>
            <th scope="col">Ella</th>
            <th scope="col">Juan</th>
        </tr>
        <tr>
            <th scope="col">&nbsp;</th>
            <th id="purchase">Purchase</th>
            <th id="location">Location</th>
            <th id="date">Date</th>
            <th id="evaluation">Evaluation</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <th scope="row">Juan</th>
            <td>Hi, I'm your first cell.</td>
            <td>I'm your third cell.</td>
            <td>I'm your fourth cell.</td>
            <td id="nested">
                <table id="table2">
                    <tr>
                        <td>cell1</td>
                        <td>cell2</td>
                        <td>cell3</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <th id="juan">Juan</th>
            <td id="purchase juan">Hi, I'm your first cell.</td>
            <td id="location juan">I'm your third cell.</td>
            <td id="date juan">I'm your fourth cell.</td>
        </tr>
    </tbody>
    <tfoot>
        <th>Juan</th>
        <td>Hi, I'm your first cell.</td>
        <td>I'm your second cell.</td>
        <td>I'm your third cell.</td>
        <td>I'm your fourth cell.</td>
    </tfoot>
</table>
```

