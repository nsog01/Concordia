function varargout = TelescopeGUI(varargin)
% TELESCOPEGUI MATLAB code for TelescopeGUI.fig
%      TELESCOPEGUI, by itself, creates a new TELESCOPEGUI or raises the existing
%      singleton*.
%
%      H = TELESCOPEGUI returns the handle to a new TELESCOPEGUI or the handle to
%      the existing singleton*.
%
%      TELESCOPEGUI('CALLBACK',hObject,eventData,handles,...) calls the local
%      function named CALLBACK in TELESCOPEGUI.M with the given input arguments.
%
%      TELESCOPEGUI('Property','Value',...) creates a new TELESCOPEGUI or raises the
%      existing singleton*.  Starting from the left, property value pairs are
%      applied to the GUI before TelescopeGUI_OpeningFcn gets called.  An
%      unrecognized property name or invalid value makes property application
%      stop.  All inputs are passed to TelescopeGUI_OpeningFcn via varargin.
%
%      *See GUI Options on GUIDE's Tools menu.  Choose "GUI allows only one
%      instance to run (singleton)".
%
% See also: GUIDE, GUIDATA, GUIHANDLES

% Edit the above text to modify the response to help TelescopeGUI

% Last Modified by GUIDE v2.5 15-Apr-2016 18:43:33

% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @TelescopeGUI_OpeningFcn, ...
                   'gui_OutputFcn',  @TelescopeGUI_OutputFcn, ...
                   'gui_LayoutFcn',  [] , ...
                   'gui_Callback',   []);
if nargin && ischar(varargin{1})
    gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end
% End initialization code - DO NOT EDIT


% --- Executes just before TelescopeGUI is made visible.
function TelescopeGUI_OpeningFcn(hObject, eventdata, handles, varargin)
% This function has no output args, see OutputFcn.
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
% varargin   command line arguments to TelescopeGUI (see VARARGIN)

% Choose default command line output for TelescopeGUI
handles.output = hObject;

% Update handles structure
guidata(hObject, handles);

% --- Outputs from this function are returned to the command line.
function varargout = TelescopeGUI_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;


% --- Executes on button press in pushbutton1.
function pushbutton1_Callback(hObject, eventdata, handles)
load('stardata.mat'); %loaded so the next star position can be passed onto the system
run('TelescopeModel');
set_param('TelescopeModel', 'SimulationCommand', 'start');
sim('TelescopeModel');




% --- Executes on button press in pushbutton2.
function pushbutton2_Callback(hObject, eventdata, handles)
set_param('TelescopeModel', 'SimulationCommand', 'stop');


% --- Executes on button press in mode.
function mode_Callback(hObject, eventdata, handles)
% hObject    handle to mode (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hint: get(hObject,'Value') returns toggle state of mode



function phiTxt_Callback(hObject, eventdata, handles)
% hObject    handle to phiTxt (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of phiTxt as text
%        str2double(get(hObject,'String')) returns contents of phiTxt as a double


% --- Executes during object creation, after setting all properties.
function phiTxt_CreateFcn(hObject, eventdata, handles)
% hObject    handle to phiTxt (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on slider movement.
function slider1_Callback(hObject, eventdata, handles)
% hObject    handle to slider1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'Value') returns position of slider
%        get(hObject,'Min') and get(hObject,'Max') to determine range of slider


% --- Executes during object creation, after setting all properties.
function slider1_CreateFcn(hObject, eventdata, handles)
% hObject    handle to slider1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: slider controls usually have a light gray background.
if isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor',[.9 .9 .9]);
end



function thetaTxt_Callback(hObject, eventdata, handles)
% hObject    handle to thetaTxt (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of thetaTxt as text
%        str2double(get(hObject,'String')) returns contents of thetaTxt as a double


% --- Executes during object creation, after setting all properties.
function thetaTxt_CreateFcn(hObject, eventdata, handles)
% hObject    handle to thetaTxt (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function filePath_Callback(hObject, eventdata, handles)
% hObject    handle to filePath (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of filePath as text
%        str2double(get(hObject,'String')) returns contents of filePath as a double


% --- Executes during object creation, after setting all properties.
function filePath_CreateFcn(hObject, eventdata, handles)
% hObject    handle to filePath (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on button press in browseBtn.
function browseBtn_Callback(hObject, eventdata, handles)
% hObject    handle to browseBtn (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% --- Executes on button press in radiobutton9 (PD).
function radiobutton9_Callback(hObject, eventdata, handles)
%references to TelescopeModel's PID controller blocks.
con1 = getSimulinkBlockHandle('TelescopeModel/PID Controller',true);
con2 = getSimulinkBlockHandle('TelescopeModel/PID Controller1',true);
set_param(con1, 'P', '0.506676436235224');
set_param(con1, 'I', '0');
set_param(con1, 'D', '3.9314301358739');
set_param(con2, 'P', '0.506676436235224');
set_param(con2, 'I', '0');
set_param(con2, 'D', '3.9314301358739');

% --- Executes on button press in radiobutton10 (PID).
function radiobutton10_Callback(hObject, eventdata, handles)
%references to TelescopeModel's PID controller blocks.
con1 = getSimulinkBlockHandle('TelescopeModel/PID Controller',true);
con2 = getSimulinkBlockHandle('TelescopeModel/PID Controller1',true);
set_param(con1, 'P', '0.480536537696423');
set_param(con1, 'I', '0.0555394282551153');
set_param(con1, 'D', '4.00054874676089');
set_param(con2, 'P', '0.480536537696423');
set_param(con2, 'I', '0.0555394282551153');
set_param(con2, 'D', '4.00054874676089');


% --- Executes on button press in radiobutton11.
function radiobutton11_Callback(hObject, eventdata, handles)
con1 = getSimulinkBlockHandle('TelescopeModel/PID Controller',true);
con2 = getSimulinkBlockHandle('TelescopeModel/PID Controller1',true);
set_param(con1, 'P', '1');
set_param(con1, 'I', '0');
set_param(con1, 'D', '0');
set_param(con2, 'P', '1');
set_param(con2, 'I', '0');
set_param(con2, 'D', '0');

% --- Executes on button press in radiobutton11.
function radiobutton12_Callback(hObject, eventdata, handles)
con1 = getSimulinkBlockHandle('TelescopeModel/PID Controller',true);
con2 = getSimulinkBlockHandle('TelescopeModel/PID Controller1',true);
set_param(con1, 'P', '1');
set_param(con1, 'I', '1');
set_param(con1, 'D', '0');
set_param(con2, 'P', '1');
set_param(con2, 'I', '1');
set_param(con2, 'D', '0');
