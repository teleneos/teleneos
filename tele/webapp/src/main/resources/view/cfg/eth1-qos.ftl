<#list map?keys as prop>
class ${prop} {
	bandwidth 1024;
	limit 2048;
	burst 2;
	priority 1;
	<#list map[prop] as op>
	client ${op.radacct.username} {
		bandwidth ${op.connectionHistory.userPackage.internetPackage.speed?string("###")};
		limit ${op.connectionHistory.userPackage.internetPackage.speed?string("###")};
		burst 2;
		priority 1;
		dst {
			${op.radacct.framedipaddress}/24;
		};
	};
	</#list>
};
</#list>
class default { bandwidth 8; };