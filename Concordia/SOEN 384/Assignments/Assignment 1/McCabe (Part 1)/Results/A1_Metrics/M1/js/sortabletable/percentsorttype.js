function percentConverter(s)
{
	var n = s;
	var i = s.lastIndexOf("%");
	if ( i != -1 )
	{
		n = s.substr(0, i);
	}

	return parseFloat(n);
}

SortableTable.prototype.addSortType("Percent", percentConverter);
