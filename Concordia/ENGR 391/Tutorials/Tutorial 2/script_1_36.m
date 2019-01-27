function b = deciTObina(d)  %Stop1--------------------------

large = 0; b(1:30)=0;

if d > 0.0
    sign=1;
else
    sign=0;
end

for i=1:15
        large = large +(2^(i-1));
end
                            
if(d>large)
    disp('ERROR: The number is too large for this function to handle')
end
                            %Stop2---------------------------
dec=abs(d); integer=floor(dec); decimal = dec-integer; num=dec; flag=0;
icount = 1;
                            %Stop3---------------------------
%convert digits to the right of the decimal point:

if(decimal~=0)              
    for j=1:15              
        temp = decimal*(2^j);
        if (temp >=1)
            p(icount)=-j; 
            icount = icount+1;
            decimal = decimal - (2^(-j));
                            %Stop4---------------------------
            if(decimal==0)
                flag=1;
            end
                            
        end
        
        if(flag==1)
            break
        end                %Stop5---------------------------
    end
    for i= 1:length(p)
        b(15-p(i))=1;
    end
end                        %Stop6---------------------------
%convert digits to the left of the decimal point:

if(integer ~= 0)
    flag=0; icount=1;
    
    for j = 15:-1:1        %Stop7---------------------------
        temp = integer/(2^j);
        if(temp>=1)        %Stop8---------------------------
            pint(icount) = j;
            icount = icount +1;
            integer = integer - 2^j;
            if(integer==1)
                flag = 1;
                pint0=0;
            end
        end
        if(flag==1)
           break 
        end
    end
    for i=1:length(pint)
       b(15-pint(i))=1; 
    end    
    if(integer == 1)
        pint((length(pint))+1) = pint0;
    end
    for i=1:length(pint)
        b(15-pint(i))=1;
    end
end


end

