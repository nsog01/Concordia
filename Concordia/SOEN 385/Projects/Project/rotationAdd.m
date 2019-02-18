function rotation = rotationAdd(x1, y1, z1, angle1, x2, y2, z2, angle2)
if((x1*x1 + y1*y1 + z1*z1) ~= 1)
    NormeCarree = (x1*x1 + y1*y1 + z1*z1);
    invNorme = (1/sqrt(NormeCarree));
    x1 = x1 * invNorme;
    y1 = y1 * invNorme;
    z1 = z1 * invNorme;
end

if((x2*x2 + y2*y2 + z2*z2) ~= 1)
    NormeCarree = (x2*x2 + y2*y2 + z2*z2);
    invNorme = (1/sqrt(NormeCarree));
    x2 = x2 * invNorme;
    y2 = y2 * invNorme;
    z2 = z2 * invNorme;
end

% convert axis-angle 1 to quaternion
w1 = cos(angle1/2);
s = sin(angle1/2);
x1 = x1 * s;
y1 = y1 * s;
z1 = z1 * s;

% convert axis-angle 2 to quaternion
w2 = cos(angle2/2);
s = sin(angle2/2);
x2 = x2 * s;
y2 = y2 * s;
z2 = z2 * s;

angleFinal = w2*w1 - x2*x1 - y2*y1 - z2*z1;
xFinal = x2*w1 + w2*x1 + y2*z1 - z2*y1;
yFinal = w2*y1 - x2*z1 + y2*w1 + z2*x1;
zFinal = w2*z1 + x2*y1 - y2*x1 + z2*w1;

% now extract the result
if(angleFinal == 1 || angleFinal == -1)
    angleFinal = 0;
    xFinal = 1; yFinal = 0; zFinal = 0;
else
    s = sqrt(1-angleFinal*angleFinal);
    angleFinal = 2 * acos(angleFinal);
    
    if (abs(s) < 0.001) s = 1;
        xFinal = xFinal / s;
        yFinal = yFinal / s;
        zFinal = zFinal / s;
    end
end

rotation = [xFinal yFinal zFinal angleFinal];
end
