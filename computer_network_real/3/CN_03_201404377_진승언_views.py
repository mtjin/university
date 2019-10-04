from django.shortcuts import render
from django.http import HttpResponse
from django.template import loader
blog_html = open('polls/html_element.txt', 'r').read()

def index(request):
    return HttpResponse(blog_html)
