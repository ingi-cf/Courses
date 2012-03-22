\NeedsTeXFormat{LaTeX2e}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%    D�finition du style principal et d'objets de styles         %%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%description of the class 
\ProvidesClass{eplDoc}[2009/09/21 Classe de documents epl, V0.1]
 
% Parent class
\LoadClass[10pt]{article} 

 
% extensions
\RequirePackage[utf8]{inputenc}					%charset : utf8
\RequirePackage{verbatim}
\RequirePackage{amssymb}		                %ensembles pr�d�finis (ex : \mathbb{R})
\RequirePackage{graphicx}
\RequirePackage{xcolor}
\RequirePackage{amsmath} 		                %Insertion d'equations
\RequirePackage{mathenv}					%systemes d'equations
\RequirePackage[hang,scriptsize,bf]{caption} 
\RequirePackage[francais]{babel} 
\RequirePackage{float}                          %package qui g�re les objets flottants exemple et gquote
\RequirePackage{fancyhdr}                       %type de pages ent�te et pied


%marges (d�but)
\addtolength{\hoffset}{-2cm}			
\addtolength{\textwidth}{4cm}			
\addtolength{\voffset}{-2cm}
\addtolength{\textheight}{3.5cm}
%marges (fin)

%%  personalisation du titre de la table des mati�res
\renewcommand{\contentsname}{Contenu du document.} 

%%d�finition d'une sim
\newcommand{\standardrule}{\rule{\larg}{1mm}}
\newcommand{\whiteline}{\vspace{12pt}}


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%                D�finition d'environements                      %%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%%%exemples
\floatstyle{ruled}
\newfloat{exemple}{htbp}{exp}[section]
\floatname{exemple}{Exemple}

%%  god quotes
\floatstyle{boxed}
\newfloat{gquote}{htbp}{gqt}[section]
\floatname{gquote}{God Quote}
\parindent0ex

%%  theorem, lemma,  proposition, corollary, proof
\newtheorem{theorem}{Th�or�me}[section]
\newtheorem{lemma}[theorem]{Lemme}
\newtheorem{proposition}[theorem]{Proposition}
\newtheorem{corollary}[theorem]{Corolaire}
\newtheorem{proof}[theorem]{Proof}


% gestion �criture de source
\usepackage{listings} 
\definecolor{colKeys}{rgb}{0,0,1} 
\definecolor{colIdentifier}{rgb}{0,0,0} 
\definecolor{colComments}{rgb}{0,0.5,1} 
\definecolor{colString}{rgb}{0.6,0.1,0.1} 

\lstset{ 
basicstyle=\ttfamily\small, % 
identifierstyle=\color{colIdentifier}, % 
keywordstyle=\color{colKeys}, % 
stringstyle=\color{colString}, % 
commentstyle=\color{colComments} 
} 

\lstset{language=java}

%fin gestion �criture de source





%%  top and foot of pages
\pagestyle{fancy}
\lhead{\courseCode \ \docType}

\newlength{\larg}
\setlength{\larg}{14.5cm}

\title{
\standardrule
\vspace{7mm}
\Huge \courseCode \ \courseName \\ \docType \\
\standardrule
\vspace{2cm}
\begin{center}
\includegraphics[height=8cm]{logo_ucl.jpg}
\end{center}
\vspace{2cm}
}

\author{\begin{tabular}{p{13.7cm}}
\docAuthor\\
\docDate
\end{tabular}\\
\hline 
}

\date{}





