# -*- coding: utf-8 -*-
"""Synthesize ORIGINAL sound effects as OGG."""
import os, math
import numpy as np
import soundfile as sf

S = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\resources\assets\dyairdrop\sounds'
os.makedirs(S, exist_ok=True)
for f in os.listdir(S):
    os.remove(os.path.join(S, f))

SR = 22050

def env(n, attack=0.01, release=0.3):
    e = np.ones(n)
    a = int(SR * attack)
    r = int(SR * release)
    if a > 0:
        e[:a] = np.linspace(0, 1, a)
    if r > 0:
        e[-r:] *= np.linspace(1, 0, r)
    return e

def save(name, data):
    sf.write(os.path.join(S, name), data.astype(np.float32), SR, format='OGG', subtype='VORBIS')
    print('wrote', name, round(len(data)/SR, 2), 's')

# 1. plane engine: brown-noise rumble + propeller pulse
n = SR * 6
rng = np.random.default_rng(7)
brown = np.cumsum(rng.normal(0, 0.02, n))
brown = brown / np.max(np.abs(brown))
t = np.arange(n) / SR
prop = 0.35 * np.sin(2 * np.pi * 32 * t) * (0.6 + 0.4 * np.sin(2 * np.pi * 0.35 * t))
rumble = 0.75 * brown + prop
rumble *= env(n, 0.4, 0.8) * 0.8
save('plane.ogg', rumble)

# 2. correct chime: two ascending pure notes
n = int(SR * 0.7)
t = np.arange(n) / SR
note1 = np.sin(2 * np.pi * 659.25 * t[:int(SR*0.28)]) * np.exp(-3 * t[:int(SR*0.28)])
note2 = np.sin(2 * np.pi * 987.77 * t[:n-int(SR*0.25)]) * np.exp(-3 * t[:n-int(SR*0.25)])
sig = np.zeros(n)
sig[:int(SR*0.28)] += note1 * 0.5
sig[int(SR*0.25):] += note2 * 0.5
save('chime-correct.ogg', sig * 0.85)

# 3. wrong buzzer: dissonant low buzz
n = int(SR * 0.55)
t = np.arange(n) / SR
buzz = (np.sign(np.sin(2 * np.pi * 138 * t)) * 0.35 + np.sign(np.sin(2 * np.pi * 146.8 * t)) * 0.35)
buzz *= env(n, 0.005, 0.2)
save('buzzer-wrong.ogg', buzz * 0.7)

# 4. keypad beep
n = int(SR * 0.14)
t = np.arange(n) / SR
beep = np.sin(2 * np.pi * 1180 * t) * env(n, 0.004, 0.05)
save('keypad-beep.ogg', beep * 0.6)

# sounds.json (original mapping, functional keys preserved)
json_data = {
    "planesound": {"subtitle": "subtitles.planesound", "sounds": [{"name": "dyairdrop:plane", "stream": False}]},
    "pwcorrect": {"subtitle": "subtitles.pwcorrect", "sounds": [{"name": "dyairdrop:chime-correct", "stream": False}]},
    "pwwrong": {"subtitle": "subtitles.pwwrong", "sounds": [{"name": "dyairdrop:buzzer-wrong", "stream": False}]},
    "check": {"subtitle": "subtitles.check", "sounds": [{"name": "dyairdrop:keypad-beep", "stream": False}]}
}
import json
with open(os.path.join(os.path.dirname(S), 'sounds.json'), 'w', encoding='utf-8') as f:
    json.dump(json_data, f, indent=2)
print('sounds.json rewritten')
